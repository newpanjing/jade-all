# jade-all
Jade,是Java应用程序访问Mysql数据库的一个小框架.

**数据库访问流程图**
<pre><code>
     ,---------------.          ,---.                                           ,----.             ,----------.
     |JavaApplication|          |DAO|                                           |Jade|             |DataServer|
     `-------+-------'          `-+-'                                           `-+--'             `----+-----'
             |                    |                                               |                     |      
             |<------------------>|                                               |                     |      
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
<ul>工程依赖：
<li>JDK1.7或更高版本</li>
<li>Maven3.x</li>
<li></li>
</ul>
**简单调用示例**
<ul>
	<li>jade-all\jar\jade.jar</li>
	<li>设置src/main/resource/db.properties文件</li>
	<li>调用JadeBootstrap.start方法，启动jade服务</li>
	<li>应用层实现DAO规范</li>
</ul>
**实现Jade的DAO规范**
<pre><code>
@DAO
public interface AccountDAO {

	@SQL(val = "Select * from Account ", type = SQLType.SELECT)
	Account query();
	
	@SQL(val = "Select * from Account ", type = SQLType.SELECT)
	List<Account> queryList();

	@SQL(val="Select * from Account Where id = ID",type = SQLType.SELECT)
	Account queryById(@SQLParam("ID")int id);
	
	@SQL(val="Update Account set name = 'NAME' where id = ID",type = SQLType.UPDATE)
	int updateById(@SQLParam("ID")int id,@SQLParam("NAME")String name);
	
	@SQL(val="Insert into Account()Values(ID,'NAME')",type = SQLType.INSERT)
	int insert(@SQLParam("ID")int id,@SQLParam("NAME")String name);
}

</code></pre>

**Java对象，实现getset方法**
<pre><code>
public class Account {
	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}
}
</code>
</pre>

<pre><code>
**测试增删改查**
public class JadeTest {
	public static void main(String[] args) {
		JadeBootstrap.start("org.jade");
		AccountDAO dao = JadeDAOService.getDao(AccountDAO.class);
		dao.deleteById(1);
		dao.insert(1, "test");
		dao.query();
		dao.queryById(1);
		dao.queryById2(1, "test");
		dao.updateById(1, "test2");
		
	}
}
</code>
</pre>
