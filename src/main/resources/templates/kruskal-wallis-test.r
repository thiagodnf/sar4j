require(pgirmess)
require(stringr)

VALUES <- @VALUES@
GROUPS <- @GROUPS@

results <- kruskal.test(VALUES, GROUPS)

chiSquared <- str_replace_all(results$statistic, "Kruskal-Wallis chi-squared", "")
pValue <- results$p.value
df <- str_replace_all(results$parameter, "df=", "")

cat("chi-squared:", chiSquared, "\n")
cat("df:", df, "\n")
cat("p-value:", pValue, "\n")