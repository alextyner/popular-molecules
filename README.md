# Popular Molecules
Given a list of chemical names, ranks them by their popularity on Wikipedia (number of page views). Additionally, works with any Wikipedia pages, not just chemicals.

## How

Extracts monthly page view counts for a list of Wikipedia articles by querying the [Wikimedia REST API](https://wikimedia.org/api/rest_v1/) and using Google's [Gson](https://github.com/google/gson) library. 

For the year of 2018 (or any year specified in `PopularMoleculesUI.SAMPLE_YEAR`), the total number of views each article received is divided by 12 months to produce a monthly average. 

Articles are ranked by their average monthly views and the resulting list is written to a tab-delimited file.

## Results

Here are the top 10 entries from [results.txt](https://raw.githubusercontent.com/alextyner/popular-molecules/master/output/ranking.txt), the output from a list of 16,823 chemical names.

| Chemical Name              | Monthly Wikipedia Views (2018) |
| -------------------------- | ------------------------------ |
| Fentanyl                   | 274127                         |
| Cocaine                    | 211200                         |
| Tramadol                   | 198221                         |
| MDMA                       | 196955                         |
| Gabapentin                 | 180420                         |
| Lysergic acid diethylamide | 178783                         |
| Methamphetamine            | 171371                         |
| Alprazolam                 | 168249                         |
| Paracetamol                | 163001                         |
| Diazepam                   | 158082                         |

**Note:** Chemical names with special characters like beta (β) or those which did not have page view data available are given a -1 ranking and pushed to the bottom of the list.