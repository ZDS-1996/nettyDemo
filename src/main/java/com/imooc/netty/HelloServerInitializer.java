package com.imooc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 
 * @author zds ����һ����ʼ������channelע���Ժ󣬻�ִ���������Ӧ�ĳ�ʼ������
 *
 */
public class HelloServerInitializer 
			extends ChannelInitializer<SocketChannel> {// SocketChannel:channel������

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		//ͨ��SocketChannelȥ��ȡ��Ӧ�Ĺܵ� ,Pipeline���ܵ�
		ChannelPipeline pipeline =channel.pipeline();
		//ͨ���ܵ����handler
		//HttpServerCodec����netty�Լ��ṩ�������࣬�������Ϊ������              
		//�����󵽷�������������Ҫ�����룬��Ӧ���ͻ���������  //HttpServerCodec:����Ĵ������Ӧ����������һ�������
		pipeline.addLast("HttpServerCodec", new HttpServerCodec());
		
		
		//����Զ����һ�������࣬���ء�hello netty~��
		pipeline.addLast("customhandler",new Customhandler());
	}

}
