package com.botree.constants;

public class QueryConstants {

	public static final String LOGIN_SQL = """
			select password from user where username=?
			""";
	
	public static final String INSERT_SQL = """
			insert into book values(?,?,?,?,?);
			""";

	public static final String SEARCH_SQL="""
			select * from book where isbn=?;
			""";

	public static final String DELETE_SQL = """
			delete from book where isbn=?;
			""";

	public static final String SHOWSQL = """
			select * from book;
			""";

}
