package sdu.equestionnaire.common;

public interface Message_Type {
	/*
	 * 0表示登录，对应的是user 1表示注册，对应的是user 2表示返回登录注册信息，对应的是string
	 * 3表示获取调查问卷列表，对应的是data数据的list 4表示分析结果 5表示获得个人信息，对应的是user
	 */
	public static final int LoginIn = 0;
	public static final int Regesiter = 1;
	public static final int returnlogin = 2;
	public static final int getdata = 3;
	public static final int getAnnaltics = 4;
	public static final int Information = 5;

}
