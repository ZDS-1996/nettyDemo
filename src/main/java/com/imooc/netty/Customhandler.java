package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author zds
 *创建自定义助手类，他需要继承SimpleChannelInboundHandler类
 */
//SimpleChannelInboundHandler：对于请求来讲，其实相当于【入站、入境】
public class Customhandler extends SimpleChannelInboundHandler<HttpObject>{
	
	//从缓冲区读数据，
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) 
			throws Exception {
		//获取channel
		Channel channel = ctx.channel();
		
		//判断请求类型是不是httpReques请求类型
		if(msg instanceof HttpRequest ) {
			//显示客户端的远程地址
			System.out.println(channel.remoteAddress());
			
			//拷贝到缓冲区
			//定义发送的数据消息
			ByteBuf content = Unpooled.copiedBuffer("Hello Netty~", CharsetUtil.UTF_8);
			
			//构建一个http响应response   FullHttpResponse:是接口
			// DefaultFullHttpResponse(version,status, validateHeaders) 										
			FullHttpResponse response = 
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,//version: http版本号
							HttpResponseStatus.OK,//status:请求成功或失败的状态，比如：200，500
							content);	//validateHeaders：响应的内容 
			
			//为响应增加数据的类型 text/plain、json
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			//为响应增加数据的长度
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
			
			//把响应刷到客户端
			ctx.writeAndFlush(response);
		}
		
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel..注册");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel..移除");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("当前的这个channel是活跃状态");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("当前的这个channel是不活跃状态");//客户端与服务端断开时
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel读取完毕时触发");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("用户事件触发时被捕获到");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel可写事件被更改");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("捕获到异常");
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("助手类添加");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("助手类移除");
		super.handlerRemoved(ctx);
	}
	
}
