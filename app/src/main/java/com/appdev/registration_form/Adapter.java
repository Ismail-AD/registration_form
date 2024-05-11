package com.appdev.registration_form;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.registration_form.databinding.UserdataLayoutBinding;
import java.util.List;

class YourAdapter extends RecyclerView.Adapter<YourAdapter.ViewHolder> {

    private List<UserInfo> userDataList;

    public YourAdapter(List<UserInfo> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout using ViewBinding
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        UserdataLayoutBinding binding = UserdataLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to views
        UserInfo userData = userDataList.get(position);
        holder.bind(userData);
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private UserdataLayoutBinding binding;

        public ViewHolder(UserdataLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserInfo userData) {
            // Bind data to views
            binding.name.setText(userData.getName());
            binding.country.setText(userData.getCountry());
            binding.skills.setText(String.join(", ", userData.getSkills()));
            binding.gender.setText(userData.getGender());
        }
    }
}

