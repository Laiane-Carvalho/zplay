package zup.com.br.zplay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class SupportActivity extends AppCompatActivity {

    /**
     * Retorno o ID do layout
     *
     * @return
     */
    abstract int layoutID();

    /**
     * Init substitui on create
     *
     * @param savedInstanceState
     */
    abstract void inicializar(Bundle savedInstanceState);

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutID());
        ButterKnife.bind(this);

        this.inicializar(savedInstanceState);
    }

}
