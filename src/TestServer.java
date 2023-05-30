/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author user
 */
public class TestServer implements Runnable {
    private int maxPacketSize = 512;
    private byte[] buffer = new byte[maxPacketSize];
    private DatagramSocket socket;

    public TestServer(int port) throws IOException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        System.out.println("Server starts");
        DefaultTableModel model = (DefaultTableModel)frame1.jTable2.getModel();
        while (true) {
            try {
                DatagramPacket packetFromClient = new DatagramPacket(buffer, buffer.length);
                socket.receive(packetFromClient); // blocking operation like Socket.accept()

                byte[] response = "got your message".getBytes();
                DatagramPacket packetToClient = new DatagramPacket(
                        response, response.length,
                        packetFromClient.getAddress(), packetFromClient.getPort());
                socket.send(packetToClient);
                
                model.setValueAt(new String(packetFromClient.getData()), frame1.jTable2.getSelectedRow(), 3);
                System.out.println("Message from client :" + new String(packetFromClient.getData()));
            } catch (IOException e) {
            }
        }
    }

    public void shutdown() {
        this.socket.close();
        Thread.currentThread().interrupt();
    }
}
