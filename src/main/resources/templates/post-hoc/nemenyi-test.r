resultPostHoc <- tryCatch({
    posthoc.kruskal.nemenyi.test(VALUES, GROUPS, "Tukey")
}, warning=function(w) {
    posthoc.kruskal.nemenyi.test(VALUES, GROUPS, "Chisquare")
})

resultPostHoc <- resultPostHoc\$p.value;

dimensions <- attr(resultPostHoc, "dim")
rows <- attr(resultPostHoc, "dimname")[[1]]
columns <- attr(resultPostHoc, "dimname")[[2]]

postHocAvailable <- TRUE
postHocDimensions <- paste(dimensions, collapse = ',')
postHocRows <- paste(rows, collapse = '\",\"')
postHocColumns <- paste(columns, collapse = '\",\"')
postHocValues <- paste(array(resultPostHoc), collapse = '\",\"')