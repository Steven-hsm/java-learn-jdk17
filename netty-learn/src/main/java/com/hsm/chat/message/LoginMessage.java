package com.hsm.chat.message;

import lombok.Data;

@Data
public class LoginMessage implements Message{

    @Override
    public byte getMessageType() {
        return 0;
    }

    @Override
    public int getSequenceId() {
        return 0;
    }

    private String userName;
    private String password;

    public LoginMessage(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
