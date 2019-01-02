package com.bw.student.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.student.R;
import com.bw.student.mvp.model.bean.Condition;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.ui.adapter
 * @time 2018/12/7 9:17
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHoder>{

    Context context;
    List<Condition> conditionList;
    public MessageAdapter(Context context, List<Condition> conditionList) {
        this.context=context;
        this.conditionList=conditionList;
    }

    @NonNull
    @Override
    public MessageViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.condition_layout, viewGroup, false);
        MessageViewHoder viewHoder = new MessageViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHoder messageViewHoder, int i) {
        Condition condition = conditionList.get(i);
        messageViewHoder.message_headPic.setImageURI(Uri.parse(condition.getStuPhoto()));

        messageViewHoder.message_name.setText(condition.getStuName());

        messageViewHoder.message_address.setText(condition.getNativePlace());

        messageViewHoder.message_work.setText(condition.getPost());

        messageViewHoder.message_graduate.setText(condition.getGraduationTime());

        messageViewHoder.message_money.setText(condition.getSalary()+"");
    }

    @Override
    public int getItemCount() {
        return conditionList.size();
    }


    class MessageViewHoder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView message_headPic;
        private final TextView message_name;
        private final TextView message_graduate;
        private final TextView message_address;
        private final TextView message_work;
        private final TextView message_money;

        public MessageViewHoder(@NonNull View itemView) {
            super(itemView);
            message_headPic = itemView.findViewById(R.id.message_HeadPic);
            message_name = itemView.findViewById(R.id.message_name);
            message_graduate = itemView.findViewById(R.id.message_graduate);
            message_address = itemView.findViewById(R.id.message_address);
            message_work = itemView.findViewById(R.id.message_work);
            message_money = itemView.findViewById(R.id.message_money);

        }
    }
}
