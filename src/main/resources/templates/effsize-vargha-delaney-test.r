require(effsize)

VALUES <- @VALUES@
GROUPS <- @GROUPS@

result <- VD.A(VALUES, GROUPS)

magnitude <- result$magnitude

cat("estimate:", result$estimate, "\n")
cat("magnitude:", magnitude, "\n")
cat("level:", attr(magnitude, "levels")[magnitude], "\n")