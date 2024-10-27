package com.prmproject.hairsalonbooking.ui.info;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.prmproject.hairsalonbooking.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HairSalonInfoFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng shopLocation = new LatLng(13.769304462426435, 109.23007734155416);
    private String shopName = "Glamour Hair Studio";
    private String shopAddress = "999 Nguyễn Huệ, Quy Nhon, Viet Nam";
    private String shopPhone = "0123456789";
    private String shopDescription = "Glamour Hair Studio là một salon tóc hiện đại và sang trọng, mang đến trải nghiệm chăm sóc tóc tuyệt vời cho mọi khách hàng. Với đội ngũ chuyên viên tóc dày dạn kinh nghiệm và nhiệt tình, chúng tôi tự hào mang đến những dịch vụ cắt tóc, tạo kiểu, nhuộm, và chăm sóc tóc chất lượng cao.";
    private String shopServices = "Tại Glamour Hair Studio, chúng tôi không chỉ là một salon tóc, mà còn là một không gian thư giãn và chăm sóc bản thân. Khi bạn bước vào, bạn sẽ được chào đón bởi một không khí ấm áp và thân thiện, cùng với sự tận tâm của đội ngũ nhân viên.";
    private String shopOpenHours = "Thứ 2 - Thứ 6: 8:00 - 20:00\nThứ 7 - Chủ Nhật: 9:00 - 18:00";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hair_salon_info, container, false);

        TextView txtShopName = view.findViewById(R.id.shopName);
        TextView txtShopAddress = view.findViewById(R.id.shopAddress);
        TextView txtShopPhone = view.findViewById(R.id.shopPhone);
        TextView txtShopDescription = view.findViewById(R.id.shopDescription);
        TextView txtShopServices = view.findViewById(R.id.shopIntro);
        TextView txtShopOpenHours = view.findViewById(R.id.shopHours);

        txtShopName.setText(shopName);
        txtShopAddress.setText(shopAddress);
        txtShopPhone.setText(shopPhone);
        txtShopDescription.setText(shopDescription);
        txtShopServices.setText(shopServices);
        txtShopOpenHours.setText(shopOpenHours);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Button btnNavigate = view.findViewById(R.id.btnNavigate);
        btnNavigate.setOnClickListener(v -> openGoogleMapsNavigation());

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        mMap.addMarker(new MarkerOptions().position(shopLocation).title(shopName).snippet(shopAddress));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shopLocation, 15));
    }


    private void openGoogleMapsNavigation() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + shopLocation.latitude + "," + shopLocation.longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(requireContext(), "Google Maps không có sẵn", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onMapReady(mMap);
        } else {
            Toast.makeText(requireContext(), "Quyền vị trí bị từ chối", Toast.LENGTH_SHORT).show();
        }
    }
}
