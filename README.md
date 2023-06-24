# Rate My Professor Data Analyzer
This assignment from school takes data from RatemyProfessor to analyze the relationship between a professor's gender and the rating they get, stored in RateMyProf Data Gendered.csv and RateMyProf Data Gendered Sample.csv. For full description, look at Comp_250_Winter_2023_Final_Project_UPDATED_getEntries().pdf. 

- Uses a Hashtable to store the data in the MyHashTable class.
- The DataAnalyzer.java class serves as a base for implementing various data analysis tasks by providing a common structure and defining the necessary methods that subclasses need to override.
- The RatingDistributionBy classes calculates the distribution of ratings and returns an average rating and the number of reviews.
- RatingBy calculates the distribution of ratings based on a specific keyword.
- GenderBy calculates the distribution of gender based on a specific keyword.
  
#Setup
Project set up
For this project, you would be using a GUI that is programmed in JavaFX, so you need to set up JavaFX
in your IDE properly.
• For Intellij user (recommended):
– Windows user: It should be already included in the SDK if you are using Java 1.8 or higher.
– Mac user: By default you laptop might be using Amazom Correto distribution, you need to
change it to Liberica distribution to support media.
1. open File → Project Structure → SDKs → Add → Download new SDKs → Select Liberica and install it
2. In your run configuration, select Liberica as your build SDK and build the project
• For Eclipse user:
– Windows user: You need to install JavaFX library manually
1. In Help menu, in Install new software wizzard you should add the new site location to
find proper software. Use ”Add” button, then in ”name” section type ”e(fx)clipse (or anything you want, it does not matter). In ”location” section type: https://download.
eclipse.org/efxclipse/updates-nightly/site/
2. Search downloadable package by applying a filter ”e(fx)clipse” you should see a list of
options (such as JavaFX SDK)
3. Install them all, after that Eclipse will restart
4. In Eclipse select the project, run Project → Preferences → Java Build Path → Add Library
→ Select JavaFX SDK, then rebuild the project, all errors should go away
– Mac user: switch to Intellij
#
