if (!require(pgirmess)){ install.packages("pgirmess")}
if (!require(stringr)){ install.packages("stringr")}
if (!require(PMCMR)){ install.packages("PMCMR")}

require(pgirmess)
require(stringr)
require(PMCMR)

alpha <- @ALPHA@

VALUES <- @VALUES@
GROUPS <- @GROUPS@

results <- kruskal.test(VALUES, GROUPS)

chiSquared <- str_replace_all(results$statistic, "Kruskal-Wallis chi-squared", "")
pValue <- results$p.value
df <- str_replace_all(results$parameter, "df=", "")
groupNames <- paste(attr(unique(GROUPS) , "levels"), collapse = '","')

postHocAvailable <- FALSE
postHocDimensions <- NULL
postHocRows <- NULL
postHocColumns <- NULL
postHocValues <- NULL

if (!is.nan(pValue) && pValue < alpha){
    @POSTHOC@
} 

cat(paste0("{"))
cat(paste0("\"chi-squared\" : \"", chiSquared, "\",\n"))
cat(paste0("\"df\" : ", df, ",\n"))
cat(paste0("\"p-value\" : \"", pValue, "\",\n"))
cat(paste0("\"group-names\" : [\"", groupNames, "\"],\n"))

cat(paste0("\"post-hoc-available\" : ", tolower(postHocAvailable), ",\n"))
cat(paste0("\"post-hoc-dimensions\" : [", postHocDimensions, "],\n"))
cat(paste0("\"post-hoc-rows\" : [\"", postHocRows, "\"],\n"))
cat(paste0("\"post-hoc-columns\" : [\"", postHocColumns, "\"],\n"))
cat(paste0("\"post-hoc-values\" : [\"", postHocValues, "\"]"))
cat("}")