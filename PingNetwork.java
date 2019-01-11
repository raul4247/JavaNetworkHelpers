import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

/**
 * 
 * PingNetwork class pings ip addresses in a network.
 *
 * @version 1.0 11 Jan 2019
 * @author Raul Mansur
 */

public class PingNetwork {

    /* The standard address and timeout for ping can be altered */
    private String stdAddress;
    private int timeOut;

    /* Standard Constructor */
    public PingNetwork() {
        this.stdAddress = "192.168.0.";
        this.timeOut = 500;
    }

    /* Constructor for customizing ping variables */
    public PingNetwork(String stdAddress, int timeOut) {
        this.timeOut = timeOut;
        this.stdAddress = stdAddress;
    }

    /* Function for attemping pinging an ip */
    public boolean attemptIp(String address) {
        InetAddress inetAddr = null;

        try {
            inetAddr = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            return false;
        }
        try {
            if (inetAddr.isReachable(this.timeOut)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /* Function for attemping pinging an ip with debugging prints */
    public boolean attemptIpDEBUG(String address) {
        InetAddress inetAddr = null;

        try {
            inetAddr = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            System.out.println("Bad ip -> " + address);
            return false;
        }
        try {
            if (inetAddr.isReachable(this.timeOut)) {
                System.out.println(address + " is ok!");
                return true;
            } else {
                System.out.println("Host " + address + " is not reachable even once.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Network error.");
            return false;
        }
    }

    /* Pings between a range in DEBUG mode */
    public void attemptPingRange(int first, int last) {
        if (first > last)
            return;

        for (int i = first; i <= last; i++)
            this.attemptIpDEBUG(this.stdAddress + "" + i);
    }

    /* Pings between a range and shows only TRUE responses */
    public void testPingRange(int first, int last) {
        if (first > last)
            return;

        for (int i = first; i <= last; i++)
            if (this.attemptIp(this.stdAddress + "" + i))
                System.out.println(this.stdAddress + "" + i + " found.");
    }

    /* Getter for timeOut */
    public int getTimeOut() {
        return this.timeOut;
    }

    /* Getter for stdAddress */
    public String getStdAddress() {
        return this.stdAddress;
    }

    /* Setter for timeOut */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /* Setter for stdAddress */
    public void SetStdAddress(String stdAddress) {
        this.stdAddress = stdAddress;
    }
}