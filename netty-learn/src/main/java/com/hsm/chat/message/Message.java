package com.hsm.chat.message;

import java.io.Serializable;

public interface Message extends Serializable {
    byte getMessageType();

    int getSequenceId();
}
