# Software Engineering: stingy.gr
![Stingy](./src/main/webapp/static/images/home_small.gif)

This repository hosts the assignment for the Software Engineering course of NTUA ECE.

## About the project
The goal of this project is to create a website that will work as a price comparison engine ("price observatory") for technology products. The system will rely upon its volunteers to record new prices and add new products and stores, which means that it will be based on the "crowdsourcing" technique. 

In addition we will create a RESTful Web API in order to allow interconnectivity with third party web apps.

## Data
We populate the database, using `src/main/sql/database.sql`, with toy products in order to check the functionality of our web-app.

Furthermore, the original idea was to create an automated process that would perform regular web scrapings to the websites of the stores in our database in order to update the prices of our products. For this purpose, we created some scripts to crawl specific pages of four of Greece's top techology products retailers. We also executed those scripts in order to gather data for our final database. This idea, though, was later abandoned since the assignment's specifications demanded the use of the crowdsourcing technique.

[Here](./WebScraping/) you can find both the data we gathered and the scripts we used.


## Contributors
| Name                  |    ID    |                   Email |
|-----------------------|:--------:|------------------------:|
| [Antoniadis Panagiotis](https://github.com/PanosAntoniadis) | 03115009 | pantoniadis97@gmail.com |
| [Bazotis Nikolaos](https://github.com/Nick-Buzz)      | 03115739 |    nbazotis97@gmail.com |
| [Georgiou Dimitrios](https://github.com/jimmyg1997)    | 03115106 |    dgeorgiou3@gmail.com |
| [Iliadis Thrasyvoulos](https://github.com/arkountos)  | 03115761 |eternallistener@gmail.com|
| [Koustas Konstantinos](https://github.com/kostakourta)  | 03115179 | konkou97@hotmail.com    |
| [Masouris Athanasios](https://github.com/ThanosM97)   | 03115189 | thanosmas97@gmail.com   |
| [Peppas Athanasios](https://github.com/Thapep)     | 03115749 | ath.v.peppas@gmail.com  |

## Setup

### Web-app
1. For Windows OS users: 

   - Install [nodejs](https://nodejs.org/en/) from the official website. 

   For Linux OS users: 
   
   - `sudo apt-get update`
   
   - `sudo apt install nodejs`
   
   -  `sudo apt install npm`
   
   **The app was developed using the following versions:**
   `nodejs == v8.10.0`
   `npm == 3.5.2`
2. **Starting the server**
    
    Inside the directory `src/main/webapp/` use the following command:  `npm run dev`
3.  You can visit our website using the following address:
    `localhost:3000`
    
    
## Technologies Used

### Web-app
   - React JS
   - Next JS
   - React Router
   - Babel compiler

### REST API
   - Java
   - Gradle
   
### Database
   - MySQL
   
### Web-scraping
   - Python
   - beautifulsoup4
   
## Project Structure

- `WebScraping/`: Files about the web-scraping
   - `products/`: Data we crawled from four of Greece's top techology products retailers
   - `scripts/`: Scripts for the crawling
- `docs/`: Project documentation (SRS, diagrams, etc)
- `gradle/`:
- `src/main/`: source code
   - `src/main/webapp/`: files about the react web app
      - `src/main/webapp/components`: react components used on multiple pages
      - `src/main/webapp/pages`: the different pages you can visit on our website
      - `src/main/webapp/static`: CSS and image files
   - `src/main/sql/`: MySQL ddl files to populate the database
      - `src/main/sql/database.sql`: DDL file with toy products and stores
      - `src/main/sql/testDatabase.sql`: DDL file without toy products and stores
   - Template
   - Template  
- `gradlew` and `gradlew.bat`: configuration files for Linux OS and Windows OS respectively
- `project.pdf`: project description (written in greek)
- `rest-api-specs.v2.pdf`: RESTful API specifications (written in greek)
    
  

