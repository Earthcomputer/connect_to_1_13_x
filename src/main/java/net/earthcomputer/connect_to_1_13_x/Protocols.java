package net.earthcomputer.connect_to_1_13_x;

public class Protocols {

    public static final int PROTOCOL_1_13_pre9 = 391;
    public static final int PROTOCOL_1_13 = 393;
    public static final int PROTOCOL_18w30a = 394;
    public static final int PROTOCOL_18w30b = 395;
    public static final int PROTOCOL_1_13_1 = 401;
    public static final int PROTOCOL_1_13_2_pre1 = 402;
    public static final int PROTOCOL_1_13_2 = 404;

    public static final int PROTOCOL_MIN = PROTOCOL_1_13_pre9, PROTOCOL_MAX = PROTOCOL_1_13_2;

    public static boolean isProtocolSupported(int protocol) {
        return protocol >= PROTOCOL_MIN && protocol <= PROTOCOL_MAX;
    }

    public static int activeProtocol = PROTOCOL_1_13;

    public static void switchProtocol(int newProtocol) {
        if (activeProtocol == newProtocol)
            return;

        activeProtocol = newProtocol;
    }

}
