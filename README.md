# readingForest
apache lucene part for reading forest project
### About Reading Forest Project
* Aims to build an efficient knowledge retrieval system which assists students in reading and learning
* Provides concept search functionality by retrieving related Youtube videos upon user’s request
* Help students to better understand readings and get knowledge beyond readings
#### Data crawling
* Youtube API and Youtube-dl API
##### Data Preprocessing and Indexing
1. extract id, title, description, tags and subtitles of videos
2. strip off timestamp, undesired characters (e.g. Russian)
3. StopWords Removing and Stemming
4. Apache Lucene used to index title, description, tags and subtitles together as a Document unit
5. Lucene used BM25 similarity algorithm
#### Retrieval and Ranking
* Multifield Query Parser
* Different weights on different fields

#### System Interface
Update Form : User can update his/her profile ![](screenshots/edit.png)
