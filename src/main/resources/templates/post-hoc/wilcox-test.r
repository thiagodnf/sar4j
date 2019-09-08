resultPostHoc <- pairwise.wilcox.test(VALUES, GROUPS, p.adj = "bonferroni", exact=F)
    
resultPostHoc <- resultPostHoc\$p.value;

dimensions <- attr(resultPostHoc, "dim")
rows <- attr(resultPostHoc, "dimname")[[1]]
columns <- attr(resultPostHoc, "dimname")[[2]]

postHocAvailable <- TRUE
postHocDimensions <- paste(dimensions, collapse = ',')
postHocRows <- paste(rows, collapse = '\",\"')
postHocColumns <- paste(columns, collapse = '\",\"')
postHocValues <- paste(array(resultPostHoc), collapse = '\",\"')