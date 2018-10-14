# This class is setup to override the default fetching for the target recipe.
# When fetching it forces PREMIRROR only fetching so that no attempts are made
# to fetch the downloads that are restricted to authenticated users only.
#
# The purpose of this class is to allow for automatation with pre-downloaded
# content or content that is available with curated/user defined pre-mirrors
# and or pre-populated downloads/ directories.

python do_fetch() {
    src_uri = (d.getVar('SRC_URI') or "").split()
    if len(src_uri) == 0:
        return

    for i in src_uri:
        if "restricted=1" in i.split(";")[1:]:
            # force the use of premirrors only, do not attempt download from any restricted uris
            d.setVar("BB_FETCH_PREMIRRORONLY", "1")
            break

    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.download()
    except bb.fetch2.NetworkAccess as e:
        if "restricted=1" in e.url.split(";")[1:]:
            # fatal on access to restricted downloads, print the url for manual download
            bb.fatal("The following download cannot be fetched automatically. " \
                "Please manually download the file and place it in the 'downloads' directory (or on an available PREMIRROR).\n" \
                "  %s" % (e.url.split(";")[0]))
        else:
            bb.fatal(str(e))
    except bb.fetch2.BBFetchException as e:
        bb.fatal(str(e))
}
