require(pgirmess)
require(stringr)
require(PMCMR)

VALUES <- @VALUES@
GROUPS <- @GROUPS@

post.results <- tryCatch({
  posthoc.kruskal.nemenyi.test(VALUES, GROUPS, "Tukey")
}, warning=function(w) {
  posthoc.kruskal.nemenyi.test(VALUES, GROUPS, "Chisquare")
})

result = post.results$p.value;

dimensions = attr(result, "dim")
rows = attr(result, "dimname")[[1]]
columns = attr(result, "dimname")[[2]]

cat("dimensions:", paste(dimensions, collapse = ';'), "\n")
cat("rows:", paste(rows, collapse = ';'), "\n")
cat("columns:", paste(columns, collapse = ';'), "\n")
cat("values:", paste(array(result), collapse = ';'), "\n")