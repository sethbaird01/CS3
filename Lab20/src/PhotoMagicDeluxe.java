public class PhotoMagicDeluxe {

    private static String passToBits(String pass) {
        final String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        String bits = "";
        for (int i = 0; i < pass.length(); i++) {
            int curr = base64.indexOf(pass.charAt(i));
            bits += String.format("%6s", Integer.toBinaryString(curr)).replace(' ', '0'); // leading zeroes
        }
        return bits;
    }

    private static Picture transform(Picture pic, LFSR lfsr){
        return PhotoMagic.transform(pic, lfsr);
    }

    public static void main(String[] args) {
        // String bits = passToBits("OPENSESAME");
        // System.out.println(bits.length() + " " + bits.equals("001110001111000100001101010010000100010010000000001100000100"));
        
        LFSR l = new LFSR(passToBits("OPENSESAME"), 58);

        Picture dcrp = new Picture("data/mystery.png");
        transform(dcrp, l).show(); // TODO decrypts mystery successfully
    }
}
