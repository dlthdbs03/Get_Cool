package com.example.get_cool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class InventoryAddActivity extends Activity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private EditText nameEditText;
    private EditText quantityEditText;
    private List<InventoryItem> inventoryList = new ArrayList<>();
    private String username; // 사용자 이름 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);

        imageView = findViewById(R.id.imageView);
        nameEditText = findViewById(R.id.editTextName);
        quantityEditText = findViewById(R.id.editTextQuantity);

        username = getUsername(); // 사용자 이름을 얻어옴

        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonSelectImage.setOnClickListener(view -> openImageChooser());

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(view -> saveInventoryItem());
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }

    private void saveInventoryItem() {
        String name = nameEditText.getText().toString();
        String quantity = quantityEditText.getText().toString();

        if (name.isEmpty() || quantity.isEmpty()) {
            Toast.makeText(this, "이름과 수량을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri imageUri = getImageUri(username, name); // 이미지 Uri 가져오기

        if (imageUri != null) {
            InventoryItem item = new InventoryItem(name, quantity, imageUri.toString());
            inventoryList.add(item);
            saveInventoryList();
            finish();
        }
    }

    private Uri getImageUri(String username, String itemName) {
        Bitmap imageBitmap = null; // 이미지 비트맵을 여기에 할당

        // 이미지를 저장하고 이미지 Uri를 반환
        return saveImage(imageBitmap, username, itemName);
    }

    // 이미지를 저장하고 이미지 Uri를 반환하는 함수
    private Uri saveImage(Bitmap imageBitmap, String username, String itemName) {
        File imageDirectory = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), username);
        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }

        String imageFileName = username + "_" + itemName + ".png";
        File imageFile = new File(imageDirectory, imageFileName);

        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 이미지 저장 중 오류 발생 시 null 반환
        }

        return Uri.fromFile(imageFile);
    }

    private void saveInventoryList() {
        try {

            // 파일 이름을 "username_list.txt" 대신 "<username>_list.txt"로 생성
            String fileName = username + "_list.txt";

            FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            for (InventoryItem item : inventoryList) {
                String line = item.getName() + "," + item.getQuantity() + "," + item.getImageUri() + "\n";
                osw.write(line);
            }
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            // 파일을 만들거나 열지 못하면 새 파일을 만들어줍니다.
            try {
                String username = this.username; // 이미 로그인 페이지에서 설정한 username 변수 사용
                String fileName = username + "_list.txt";
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getUsername() {
        return LoginActivity.getUsername(); // 예시로 사용자 이름을 하드코딩했습니다. 실제로는 사용자 이름을 얻어오는 방법을 구현해야 합니다.
    }
}
