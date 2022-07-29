package com.example.crex;

/*import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



    public class RvAdapterMultiView extends RecyclerView.Adapter {

        private List<Match> itemClassList;

        // public constructor for this class
        public RvAdapterMultiView(List<Match> itemClassList)
        {
            this.itemClassList = itemClassList;
        }

        // Override the getItemViewType method.
        // This method uses a switch statement
        // to assign the layout to each item
        // depending on the viewType passed

        @Override
        public int getItemViewType(int position)
        {
            switch (itemClassList.get(position).getViewType()) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                default:
                    return -1;
            }
        }

        // Create classes for each layout ViewHolder.

        class LayoutOneViewHolder
                extends RecyclerView.ViewHolder {

            private TextView textview;
            private LinearLayout linearLayout;

            public LayoutOneViewHolder(@NonNull View itemView)
            {
                super(itemView);

                // Find the Views
                textview = itemView.findViewById(R.id.text);
                linearLayout
                        = itemView.findViewById(R.id.linearlayout);
            }

            // method to set the views that will
            // be used further in onBindViewHolder method.
            private void setView(String text)
            {

                textview.setText(text);
            }
        }

        // similarly a class for the second layout is also
        // created.

        class LayoutTwoViewHolder
                extends RecyclerView.ViewHolder {

            private ImageView icon;
            private TextView text_one, text_two;
            private LinearLayout linearLayout;

            public LayoutTwoViewHolder(@NonNull View itemView)
            {
                super(itemView);
                icon = itemView.findViewById(R.id.image);
                text_one = itemView.findViewById(R.id.text_one);
                text_two = itemView.findViewById(R.id.text_two);
                linearLayout
                        = itemView.findViewById(R.id.linearlayout);
            }

            private void setViews(int image, String textOne,
                                  String textTwo)
            {
                icon.setImageResource(image);
                text_one.setText(textOne);
                text_two.setText(textTwo);
            }
        }

        // In the onCreateViewHolder, inflate the
        // xml layout as per the viewType.
        // This method returns either of the
        // ViewHolder classes defined above,
        // depending upon the layout passed as a parameter.

        @NonNull
        @Override
        public RecyclerView.ViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent,
                           int viewType)
        {
            switch (viewType) {
                case LayoutOne:
                    View layoutOne
                            = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.layout_one, parent,
                                    false);
                    return new LayoutOneViewHolder(layoutOne);
                case LayoutTwo:
                    View layoutTwo
                            = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.layout_two, parent,
                                    false);
                    return new LayoutTwoViewHolder(layoutTwo);
                default:
                    return null;
            }
        }

        // In onBindViewHolder, set the Views for each element
        // of the layout using the methods defined in the
        // respective ViewHolder classes.

        @Override
        public void onBindViewHolder(
                @NonNull RecyclerView.ViewHolder holder,
                int position)
        {

            switch (itemClassList.get(position).getViewType()) {
                case LayoutOne:

                    String text
                            = itemClassList.get(position).getText();
                    ((LayoutOneViewHolder)holder).setView(text);

                    // The following code pops a toast message
                    // when the item layout is clicked.
                    // This message indicates the corresponding
                    // layout.
                    ((LayoutOneViewHolder)holder)
                            .linearLayout.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)
                                        {

                                            Toast
                                                    .makeText(
                                                            view.getContext(),
                                                            "Hello from Layout One!",
                                                            Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    });

                    break;

                case LayoutTwo:
                    int image
                            = itemClassList.get(position).geticon();
                    String text_one
                            = itemClassList.get(position).getText_one();
                    String text_two
                            = itemClassList.get(position).getText_two();
                    ((LayoutTwoViewHolder)holder)
                            .setViews(image, text_one, text_two);
                    ((LayoutTwoViewHolder)holder)
                            .linearLayout.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)
                                        {

                                            Toast
                                                    .makeText(
                                                            view.getContext(),
                                                            "Hello from Layout Two!",
                                                            Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    });
                    break;
                default:
                    return;
            }
        }

        // This method returns the count of items present in the
        // RecyclerView at any given time.

        @Override
        public int getItemCount()
        {
            return itemClassList.size();
        }
    }

}*/
