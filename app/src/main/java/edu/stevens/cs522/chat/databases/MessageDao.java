package edu.stevens.cs522.chat.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import edu.stevens.cs522.chat.entities.Chatroom;
import edu.stevens.cs522.chat.entities.Message;
import edu.stevens.cs522.chat.entities.Peer;

// TODO add annotations for Repository pattern
@Dao
public interface MessageDao {

    @Query("SELECT m.* FROM Message AS m, Chatroom AS c WHERE m.chatroom=c.name AND m.chatroom=:chatroom")
    public abstract LiveData<List<Message>> fetchAllMessages(String chatroom);

    @Query("SELECT m.* FROM Message AS m, Peer AS p WHERE m.sender=p.name AND m.sender=:peerName")
    public LiveData<List<Message>> fetchMessagesFromPeer(String peerName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void persist(Message message);

}
