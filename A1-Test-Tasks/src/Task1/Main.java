package Task1;



public class Main {
    public static void main( String args[] ) {
        String num = "0.0.0.255";
        long result = ipToNum(num);
        System.out.println(num+" is in int "+result);
        System.out.println(result+" is in ip "+numToIp(result));
    }


    public static long ipToNum(String ip) {

        long ipNum = -1;

        String[] octets = ip.split( "\\." );

        int[] octetsNum = new int[] {
                Integer.parseInt( octets[ 0 ] ),
                Integer.parseInt( octets[ 1 ] ),
                Integer.parseInt( octets[ 2 ] ),
                Integer.parseInt( octets[ 3 ] )
        };

        ipNum = ( octetsNum[ 0 ] * (int)Math.pow( 256, 3 ) ) + ( octetsNum[ 1 ] * (int)Math.pow( 256, 2 ) ) + ( octetsNum[ 2 ] * 256 ) + ( octetsNum[ 3 ] );

        return ipNum;
    }

    public static String numToIp(long ipNum) {

        String ipAddr = ( ( ipNum >> 24 ) & 0xFF ) + "." + ( ( ipNum >> 16 ) & 0xFF ) + "." + ( ( ipNum >> 8 ) & 0xFF ) + "." + ( ipNum & 0xFF );

        return ipAddr;
    }


}
