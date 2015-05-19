package com.asia.commonutil.util;

import android.os.FileObserver;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public class FileWatcher extends FileObserver{

    private HandlerThread thread;
    private Handler handler;
    private EventAdapter adapter;
    private String tag = null;
    public FileWatcher(String path, String tag, EventAdapter adapter) {
        super(path);
        this.tag = tag;
        DLog.v("被监听的文件路径:" + path);
        this.adapter = adapter;
        initHandler(path);
    }

    private void initHandler(String path) {
        thread = new HandlerThread(path);
        thread.start();
        Handler.Callback callback = new Callback() {
            
            @Override
            public boolean handleMessage(Message msg) {
                String path = (String)msg.obj;
                int event = msg.what;
                switch (event) {
                    case FileObserver.ACCESS:           //1
                        adapter.onAeecss(path);
                        break;
                    case FileObserver.ATTRIB:           //4
                        adapter.onAttrib(path);
                        break;
                    case FileObserver.CLOSE_NOWRITE:    //16
                        adapter.onCloseNoWrite(path);
                        break;
                    case FileObserver.CLOSE_WRITE:      //8
                        adapter.onCloseWrite(path);
                        break;
                    case FileObserver.CREATE:           //256
                        adapter.onCreate(path);
                        break;
                    case FileObserver.DELETE:           //512
                        adapter.onDelete(path);
                        break;
                    case FileObserver.DELETE_SELF:      //1024
                        adapter.onDelete(path);
                        break;
                    case FileObserver.MODIFY:           //2
                        adapter.onModify(path);
                        break;
                    case FileObserver.MOVE_SELF:        //2048
                        adapter.onModify(path);
                        break;
                    case FileObserver.MOVED_FROM:       //64
                        adapter.onMoveFrom(path);
                        break;
                    case FileObserver.MOVED_TO:         //128
                        adapter.onMoveTo(path);
                        break;
                    case FileObserver.OPEN:             //32
                        adapter.onOpen(path);
                        break;
                    default:
                        break;
                }
                return false;
            }
        };
        handler = new Handler(thread.getLooper(), callback);
    }
    
    @Override
    public void onEvent(int event, String path) {
        int el = event & FileObserver.ALL_EVENTS;
        handler.obtainMessage(el, path).sendToTarget();
    }
    
    public static class EventAdapter{
        public EventAdapter(){}
        
        public void onAeecss(String path){};
        
        public void onModify(String path){};
        
        public void onAttrib(String path){};
        
        public void onCloseWrite(String path){};
        
        public void onCloseNoWrite(String path){};
        
        public void onOpen(String path){};
        
        public void onMoveFrom(String path){};
        
        public void onMoveTo(String path){};
        
        public void onCreate(String path){};
        
        public void onDelete(String path){};
        
        public void onDeleteSelf(String path){};
        
        public void onMoveSelf(String path){};
    }
}
