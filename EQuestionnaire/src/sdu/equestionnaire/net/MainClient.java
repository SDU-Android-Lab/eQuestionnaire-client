package sdu.equestionnaire.net;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import sdu.equestionnaire.info.HostInfo;

public class MainClient {
	public ConnectFuture cf;
	public NioSocketConnector connector;

	public void init() {
		// Create TCP/IP connection
		connector = new NioSocketConnector();

		// ����������ݵĹ�����
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();

		// �趨�����������һ��һ��(/r/n)�Ķ�ȡ���
		chain.addLast("codec", new ProtocolCodecFilter(
				new ObjectSerializationCodecFactory()));

		// ����������Ϣ��������һ��SamplMinaServerHander����
		connector.setHandler(new SamplMinaClientHandler());

		// set connect timeout
		connector.setConnectTimeout(30);
		
		// ���ӵ���������
		cf = connector
				.connect(new InetSocketAddress(HostInfo.IP, HostInfo.Post));

		// Wait for the connection attempt to be finished.
		cf.awaitUninterruptibly();

	}

	public void sendmes(Object obj) {

		cf.getSession().write(obj);

	}

	public Object getMessage() {
		return cf.getSession().read();
	}

	public void close() {
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}

}
