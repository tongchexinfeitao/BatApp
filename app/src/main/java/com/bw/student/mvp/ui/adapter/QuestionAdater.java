package com.bw.student.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.student.R;
import com.bw.student.mvp.model.bean.ShowBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.ui.adapter
 * @time 2018/12/7 13:35
 */
public class QuestionAdater extends RecyclerView.Adapter<QuestionAdater.QuestionHolder> {

    private Context context;
    private List<ShowBean> result;

    public QuestionAdater(Context context, List<ShowBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuestionHolder(LayoutInflater.from(context).inflate(R.layout.queston_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder questionHolder, int i) {

        questionHolder.mAnswerText.setText(result.get(i).getAnswer());
        questionHolder.mQuestionText.setText(result.get(i).getProblem());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class QuestionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.question_text)
        TextView mQuestionText;
        @BindView(R.id.answer_text)
        TextView mAnswerText;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);


            ButterKnife.bind(this, itemView);

        }


    }
}
