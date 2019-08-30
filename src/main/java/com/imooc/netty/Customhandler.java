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
 *�����Զ��������࣬����Ҫ�̳�SimpleChannelInboundHandler��
 */
//SimpleChannelInboundHandler������������������ʵ�൱�ڡ���վ���뾳��
public class Customhandler extends SimpleChannelInboundHandler<HttpObject>{
	
	//�ӻ����������ݣ�
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) 
			throws Exception {
		//��ȡchannel
		Channel channel = ctx.channel();
		
		//�ж����������ǲ���httpReques��������
		if(msg instanceof HttpRequest ) {
			//��ʾ�ͻ��˵�Զ�̵�ַ
			System.out.println(channel.remoteAddress());
			
			//������������
			//���巢�͵�������Ϣ
			ByteBuf content = Unpooled.copiedBuffer("Hello Netty~", CharsetUtil.UTF_8);
			
			//����һ��http��Ӧresponse   FullHttpResponse:�ǽӿ�
			// DefaultFullHttpResponse(version,status, validateHeaders) 										
			FullHttpResponse response = 
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,//version: http�汾��
							HttpResponseStatus.OK,//status:����ɹ���ʧ�ܵ�״̬�����磺200��500
							content);	//validateHeaders����Ӧ������ 
			
			//Ϊ��Ӧ�������ݵ����� text/plain��json
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			//Ϊ��Ӧ�������ݵĳ���
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
			
			//����Ӧˢ���ͻ���
			ctx.writeAndFlush(response);
		}
		
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel..ע��");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel..�Ƴ�");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("��ǰ�����channel�ǻ�Ծ״̬");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("��ǰ�����channel�ǲ���Ծ״̬");//�ͻ��������˶Ͽ�ʱ
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel��ȡ���ʱ����");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�û��¼�����ʱ������");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel��д�¼�������");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�����쳣");
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("���������");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("�������Ƴ�");
		super.handlerRemoved(ctx);
	}
	
}
