package com.bw.student.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.student.R;
import com.bw.student.mvp.model.bean.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.ui.adapter
 * @time 2018/12/6 19:57
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextHolder> {

    private final ArrayList<Boolean> isClicks;
    private Context context;
    private List<Department> result;

    public TextAdapter(List<Department> result, Context context) {
        this.result = result;
        this.context = context;

        //3、为集合添加值
        isClicks = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            isClicks.add(false);
        }
        isClicks.set(0, true);
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TextHolder(LayoutInflater.from(context).inflate(R.layout.text_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TextHolder textHolder, final int i) {
        textHolder.mTextItem.setText(result.get(i).getDepName());


        textHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                int position = textHolder.getLayoutPosition(); // 1
                for (int i = 0; i < isClicks.size(); i++) {
                    isClicks.set(i, false);
                }
                isClicks.set(position, true);
                notifyDataSetChanged();

            }
        });

        if (isClicks.get(i)){
            textHolder.itemView.setSelected(true);
            callBack.onCallBack(result.get(i).getId());
        }else {
            textHolder.itemView.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class TextHolder extends RecyclerView.ViewHolder {

        TextView mTextItem;

        public TextHolder(@NonNull View itemView) {
            super(itemView);

            mTextItem = itemView.findViewById(R.id.mtext_item);
        }
    }

    private CallBack callBack;

    public interface CallBack {
        void onCallBack(int position);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
