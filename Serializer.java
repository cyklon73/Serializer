

/**

 * The Serializer class provides methods to serialize and deserialize
 * various primitive data types in Java.
 * <p>This class allows for easy conversion of primitive data types
 * to byte arrays and vice versa, enabling efficient data storage,
 * transmission, and interoperability.</p>
 * @see <a href="https://github.com/cyklon73/Serializer">GitHub Repository</a>
 * @version 1.1.0
 * @since July 7, 2023
 * @author Cyklon73
 *
 * @license MIT License
 * This software is released under the MIT License.
 * See the LICENSE file for more information.
 */
public class Serializer {

    public static byte[] asArray(byte val) {
        return new byte[] {val};
    }

    public static byte[] asArray(byte... val) {
        return val;
    }

    /**
     * serialize every primitive type
     * @param obj the primitive type
     * @return the byte array as result
     */
    public static byte[] serialize(Object obj) {
        byte[] arr;
        if (obj instanceof Byte) arr = serializeByte((Byte) obj);
        else if (obj instanceof Short) arr = serializeShort((Short) obj);
        else if (obj instanceof Integer) arr = serializeInt((Integer) obj);
        else if (obj instanceof Long) arr = serializeLong((Long) obj);
        else if (obj instanceof Float) arr = serializeFloat((Float) obj);
        else if (obj instanceof Double) arr = serializeDouble((Double) obj);
        else if (obj instanceof Boolean) arr = serializeBool((Boolean) obj);
        else if (obj instanceof Character) arr = serializeChar((Character) obj);
        else if (obj instanceof String) arr = serializeString((String) obj);
        else throw new IllegalArgumentException("Only primitives and Strings allowed!");
        return arr;
    }

    public static byte[] serializeByte(byte val) {
        return asArray(val);
    }

    public static byte deserializeByte(byte[] val) {
        return val[0];
    }

    public static byte[] serializeShort(short val) {
        return asArray(
                (byte) (val >>> 8),
                (byte) val);
    }

    public static short deserializeShort(byte[] val) {
        return (short) ((0xff & val[0]) << 8 |
                        (0xff & val[1]));
    }

    public static byte[] serializeInt(int val) {
        return asArray(
                (byte) (val >>> 24),
                (byte) (val >>> 16),
                (byte) (val >>> 8),
                (byte) val);
    }

    public static int deserializeInt(byte[] val) {
        return  (0xff & val[0]) << 24 |
                (0xff & val[1]) << 16 |
                (0xff & val[2]) << 8 |
                (0xff & val[3]);
    }

    public static byte[] serializeLong(long val) {
        return asArray(
                (byte) (val >>> 56),
                (byte) (val >>> 48),
                (byte) (val >>> 40),
                (byte) (val >>> 32),
                (byte) (val >>> 24),
                (byte) (val >>> 16),
                (byte) (val >>> 8),
                (byte) val);
    }

    public static long deserializeLong(byte[] val) {
        return  (0xffL & val[0]) << 56 |
                (0xffL & val[1]) << 48 |
                (0xffL & val[2]) << 40 |
                (0xffL & val[3]) << 32 |
                (0xffL & val[4]) << 24 |
                (0xffL & val[5]) << 16 |
                (0xffL & val[6]) << 8 |
                (0xffL & val[7]);
    }


    public static byte[] serializeFloat(float val) {
        return serializeInt(Float.floatToIntBits(val));
    }

    public static float deserializeFloat(byte[] val) {
        return Float.intBitsToFloat(deserializeInt(val));
    }

    public static byte[] serializeDouble(double val) {
        return serializeLong(Double.doubleToLongBits(val));
    }

    public static double deserializeDouble(byte[] val) {
        return Double.longBitsToDouble(deserializeLong(val));
    }

    public static byte[] serializeBool(boolean val) {
        return asArray((byte) (val ? 1 : 0));
    }

    public static boolean deserializeBool(byte[] val) {
        return val[0]==1;
    }

    public static byte[] serializeChar(char val) {
        return serializeInt(val);
    }

    public static char deserializeChar(byte[] val) {
        return (char) deserializeInt(val);
    }

    public static byte[] serializeString(String val) {
        return val.getBytes();
    }

    public static String deserializeString(byte[] val) {
        return new String(val);
    }

}
