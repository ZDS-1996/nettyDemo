package com.imooc.netty;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author zds
 * @Description: �ͻ��˷�һ�����󣬷������᷵��Hello Netty
 *
 */
public class HelloServer {

	public static void main(String[] args) throws Exception {
		// ����һ���߳���
		//���߳��飬���ڽ��ܿͻ��˵����ӣ����ǲ����κδ������ϰ�һ����������
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		//���߳��飬���߳��������񶪸������ô��߳���ȥ������
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
					
			//netty�������Ĵ�����ServerBootstrap ��һ�������࣬�� ��springBoot��@ServerBootstrap��������ģ���Ҫ����
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			
			serverBootstrap.group(bossGroup, workerGroup)//�������߳������õ��������������У�Ȼ��Ͳ���Ҫ���ǹ���
							.channel(NioServerSocketChannel.class)//Ϊ����������Channel��������nio��˫��ͨ��
							.childHandler(new HelloServerInitializer());//���ô�����̳߳ص��������ʼ���� �����Ӵ����������ڴ���workerGroup��
			
			//���Ȱ�һ���˿ڣ�Ȼ������һ��ͬ���ķ�ʽ
			//����server,��������8088Ϊ�˿ںţ�ͬʱ������ʽΪͬ��
			ChannelFuture channelFuture =serverBootstrap.bind(8088).sync();
			
			//�����ر�channel�Ƿ�ر�
			//�����رյ�channel������Ϊͬ����ʽ
			channelFuture.channel()//��ȡһ��channel�ܵ�
							.closeFuture()//�ر�
							.sync();//����ͬ����ʽ
		} finally {
			//�رշ�����֮������Ҫ�ر������߳���
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	
	}


}
