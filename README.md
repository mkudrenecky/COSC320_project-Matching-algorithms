# Hello! Welcome the COSC 320 Project Repo.
Here you can find the milestone information and contribute to the algorithm source code!
## Topic
Keyword replacement in a corpus: In text analytics, often it is required that a set of keywords are replaced 
with a given set for the documents in hand. For example, on Twitter, people write a lot of abbreviations. When one 
requires to analyze the tweets, (s)he should find all the abbreviations in a given list of abbreviations (e.g. ASAP, 
won’t) and replace all these brief terms in the tweets with its proper phrase/keywords (e.g. ASAP -> As soon as 
possible, or won’t ->  will not).  Your job is  to  design  an  algorithm that  finds  all  of  the  keywords  that  are  in  the 
abbreviation list in each tweet, and then replace them with the appropriate given keyword/phrase. The number of 
tweets  can  be  millions  and  the  list  of  keywords  can  be  hundreds.  A  naïve  approach  is  that  for  each  tweet, your 
algorithm checks for all of the elements in the abbreviated list and replaces them. Other than the naïve approach, 
design a better algorithm and apply the required four steps explained in the first page.  