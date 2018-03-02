# jade-all
Jade,是Java应用程序访问Mysql数据库的一个小框架.

**数据库访问流程图**
<pre><code>
     ,---------------.          ,---.                                           ,----.             ,----------.
     |JavaApplication|          |DAO|                                           |Jade|             |DataServer|
     `-------+-------'          `-+-'                                           `-+--'             `----+-----'
             |                    |                                               |                     |      
             |------------------->|                                               |                     |      
             |                    |                                               |                     |      
             |                    |        1.Implement jdade specification        |                     |      
             |                    |----------------------------------------------->                     |      
             |                    |                                               |                     |      
             |                    |                                               | 2.Commit sql request|      
             |                    |                                               | -------------------->      
             |                    |                                               |                     |      
             |                    |                                               |   3.Return result   |      
             |                    |                                               | <- - - - - - - - - -       
             |                    |                                               |                     |      
             |                    |4.To convert a database object to a Java object|                     |      
             |                    | according to the DAO specification            |                     |      
             |                    |<- - - - - - - - - - - - - - - - - - - - - - - -                     |      
     ,-------+-------.          ,-+-.                                           ,-+--.             ,----+-----.
     |JavaApplication|          |DAO|                                           |Jade|             |DataServer|
     `---------------'          `---'                                           `----'             `----------'
</code></pre>
**快速开始**
 工程依赖：
*JDK1.8
*  
