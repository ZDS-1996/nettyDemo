package com.imooc.netty;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author zds
 * @Description: 客户端发一个请求，服务器会返回Hello Netty
 *
 */
public class HelloServer {

	public static void main(String[] args) throws Exception {
		// 定义一对线程组
		//主线程组，用于接受客户端的连接，但是不做任何处理，跟老板一样，不做事
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		//从线程组，主线程组会把任务丢给他，让从线程组去做任务
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
					
			//netty服务器的创建，ServerBootstrap 是一个启动类，他 跟springBoot的@ServerBootstrap是有区别的，不要混淆
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			
			serverBootstrap.group(bossGroup, workerGroup)//将主从线程组设置到服务器启动器中，然后就不需要我们管了
							.channel(NioServerSocketChannel.class)//为服务器设置Channel，即设置nio的双向通道
							.childHandler(new HelloServerInitializer());//设置处理从线程池的助手类初始化器 ，即子处理器，用于处理workerGroup；
			
			//首先绑定一个端口，然后设置一个同步的方式
			//启动server,并且设置8088为端口号，同时启动方式为同步
			ChannelFuture channelFuture =serverBootstrap.bind(8088).sync();
			
			//监听关闭channel是否关闭
			//监听关闭的channel，设置为同步方式
			channelFuture.channel()//获取一个channel管道
							.closeFuture()//关闭
							.sync();//设置同步方式
		} finally {
			//关闭服务器之后，我们要关闭两个线程组
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	
	}


}
