package edu.stevens.cs522.chat.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.stevens.cs522.chat.databases.ChatDatabase;
import edu.stevens.cs522.chat.entities.Message;
import edu.stevens.cs522.chat.entities.Peer;

public class PeerViewModel extends AndroidViewModel {

    public final static String TAG = PeerViewModel.class.getCanonicalName();

    private ChatDatabase chatDatabase;

    private LiveData<List<Message>> messages;

    private Peer currentPeer;

    public PeerViewModel(Application context) {
        super(context);
        Log.d(TAG, "Getting database in PeerViewModel");
        chatDatabase = ChatDatabase.getInstance(context);
    }

    // TODO finish this
    public LiveData<List<Message>> fetchAllMessages(@NonNull Peer peer) {
        if(currentPeer==null || !currentPeer.name.equals(peer.name)){
            this.currentPeer=peer;
            messages=chatDatabase.messageDao().fetchMessagesFromPeer(currentPeer.name);
        }
        return messages;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        Log.d(TAG, "Clearing PeerViewodel...");
        chatDatabase = null;
    }


}
