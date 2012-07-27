package sdu.equestionnaire.net;

import sdu.equestionnaire.common.Message_Type;
import sdu.equestionnaire.common.Messages;
import sdu.equestionnaire.common.Type;
import sdu.equestionnaire.common.User;
import sdu.equestionnaire.info.UserInfo;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       UserInfo.m_cliient=new MainClient();
       UserInfo.m_cliient.init();
       User p = new User();
		p.setType(Type.vip);
		p.setId(111);
		p.setPassword("222222");
		p.setName("dog");

		Messages msg = new Messages(Message_Type.Regesiter, p);
		UserInfo.m_cliient.sendmes(msg);
		
	}

}
