/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
/**
 *
 * @author user
 */
public class TestClient implements Runnable {
    private DatagramSocket socket;
    private InetAddress address; // localhost
    private int port; //server port - 8080
    private BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>();
    private int maxPacketSize = 512;
    private byte[] buffer =  new byte[maxPacketSize];
    private int[] timeouts = {11, 29, 73, 277, 997};

    public TestClient(String ip, int port) throws IOException {
        this.address = InetAddress.getByName(ip);
        this.port = port;
        this.socket = new DatagramSocket();
    }

    @Override
    public void run() {
        System.out.println("Client starts");
        while(true) {
            try {
                byte[] message = queue.take();
                String messageStr = new String(message); // преобразуем массив байтов в строку
                String[] numbersStr = messageStr.split(","); // разбиваем строку на массив строк по разделителю ","
                double[] numbers = new double[numbersStr.length]; // создаем массив чисел формата double
                for (int i = 0; i < numbersStr.length; i++) {
                    numbers[i] = Double.parseDouble(numbersStr[i]);
                    //System.out.println("test1:" + numbers[i]);// преобразуем каждую строку в число формата double
                }
     //===================================================================           
        //double result = 0;
                MyThread Thread1[] = new MyThread[5];
                Thread A[] = new Thread[5];

                for(int i = 0; i < 5; i ++){

                    double start = numbers[0];

                    double end = numbers[1];

                    double step = numbers[2];

                    Thread1[i] = new MyThread(end,start,step);
                    //System.out.println(Thread1[i].Tend);
                    A[i] = new Thread(Thread1[i]);
                    A[i].start();
                    try {
                        A[i].join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(frame1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //System.out.println(Thread1[i].Tresult);

                    //model.setValueAt(Thread1[i].Tresult, i, 3);
                }
                String strResult = Double.toString(Thread1[1].Tresult);
                System.out.println("string res:" + strResult);
                //byte[] resultMessage = ByteBuffer.allocate(8).putDouble(Thread1[1].Tresult).array();
                byte[] resultMessage = strResult.getBytes();
                System.out.println("res:" + resultMessage);
                System.out.println("res2:" + new String(resultMessage));
//===============================================================================================
                DatagramPacket packetToServer = new DatagramPacket(resultMessage, resultMessage.length,
                        address, port);
                
                for (int i = 0; i < timeouts.length ; i++) {
                    try {
                    socket.setSoTimeout(timeouts[i]);
                    socket.send(packetToServer);

                    DatagramPacket packetFromServer = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packetFromServer);
                    System.out.println(new String(packetFromServer.getData()));
                    break;
                    } catch (IOException e) {
                        System.out.println("Fail: too long waiting");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean push(byte[] message) {
        if (message.length < maxPacketSize) {
            queue.add(message);
            return true;
        }
        return false;
    }

    public void stop() {
        socket.close();
        Thread.currentThread().interrupt();
    }
}
