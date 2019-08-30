package com.imooc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 
 * @author zds 这是一个初始化器，channel注册以后，会执行里面的相应的初始化方法
 *
 */
public class HelloServerInitializer 
			extends ChannelInitializer<SocketChannel> {// SocketChannel:channel的类型

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		//通过SocketChannel去获取对应的管道 ,Pipeline：管道
		ChannelPipeline pipeline =channel.pipeline();
		//通过管道添加handler
		//HttpServerCodec是由netty自己提供的助手类，可以理解为拦截器              
		//当请求到服务器，我们需要做解码，响应到客户端做编码  //HttpServerCodec:请求的处理和响应，针对这个做一个编解码
		pipeline.addLast("HttpServerCodec", new HttpServerCodec());
		
		
		//添加自定义的一个助手类，返回“hello netty~”
		pipeline.addLast("customhandler",new Customhandler());
	}

}
