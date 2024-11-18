package com.hsm.chatinaction.server.handler;

import com.hsm.chatinaction.message.GroupJoinRequestMessage;
import com.hsm.chatinaction.message.GroupJoinResponseMessage;
import com.hsm.chatinaction.server.session.Group;
import com.hsm.chatinaction.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * className: GroupJoinRequestMessageHandler
 * description:
 * date: 2024/6/12-8:05
 * <p>
 * project: netty-demo
 * package: com.hsm.chatinaction.server.handler
 * email: 1085844536@qq.com
 * version:
 *
 * @author WangGuojian
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(), msg.getUsername());
        if (group != null) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群加入成功"));
        } else {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群不存在"));
        }
    }
}
