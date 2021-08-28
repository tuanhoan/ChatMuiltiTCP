/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghost.chat; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.net.Socket;
import javax.swing.JTextPane;

/**
 *
 * @author tuanHoan
 */
public class ChatMessageClient {
    private Socket socket;
    public JTextPane txtMessageBoard;
    private DataOutputStream out = null;
    private DataInputStream reader = null;

    public ChatMessageClient(Socket socket, JTextPane txtMessageBoard) throws IOException {
        this.socket = socket;
        this.txtMessageBoard = txtMessageBoard;
        out = new DataOutputStream(socket.getOutputStream());
        reader = new DataInputStream(socket.getInputStream());
        received();
    }
    private void received(){ 
        Thread th = new Thread(){
            public void run(){
                while(true){
                    try {
                        String line = reader.readUTF(); //Nhận tin nhắn từ server
                        if(line!=null){
                            txtMessageBoard.setText(txtMessageBoard.getText()+"\n" + line);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        };
        th.start();
    }
    public void send(String msg, String key) throws IOException{
        //String current = txtMessageBoard.getText();
        //txtMessageBoard.setText(current + "\n" +  msg);
        msg = new AES().enAES(msg, key);  //Mã hóa tin nhắn bằng AES
        out.writeUTF(msg);  //Gửi lên server
    }
    public void close(){
        try {
            out.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
        }
    }
}
