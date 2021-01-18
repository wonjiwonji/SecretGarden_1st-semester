package com.example.shingubotanic;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class azalea extends DialogFragment implements View.OnClickListener{

    public static final String TAG_EVENT_DIALOG ="azalea"; //수정  ""

    public azalea(){} //철쭉원(19)

    public static azalea getInstance() {
        azalea aza = new azalea(); //수정 변수명 스펠링 앞세개
        return aza; //수정
    }

    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sacedInstanceState){
        View v = inflater.inflate(R.layout.azalea,container);// xml명 변경
        Button cancel =(Button) v.findViewById(R.id.cancel);

        final ImageView aza =(ImageView) v.findViewById(R.id.aza);
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://shingubotanic-92fac.appspot.com");
        StorageReference storageRef = storage.getReference();
        String azalea = "Preparing.jpg";
        storageRef.child(azalea).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시

                Glide.with(Objects.requireNonNull(getContext()))
                        .load(uri)
                        .into(aza);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Log.d("TEST", "error" + exception.getLocalizedMessage());
            }
        });

        cancel.setOnClickListener(this);
        setCancelable(false);
        return v;

    }

    @Override
    public void onClick(View v) {
        dismiss();

    }
}
