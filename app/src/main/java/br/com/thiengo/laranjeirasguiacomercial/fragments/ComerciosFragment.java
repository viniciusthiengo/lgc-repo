package br.com.thiengo.laranjeirasguiacomercial.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.adapters.ComerciosAdapter;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;
import br.com.thiengo.laranjeirasguiacomercial.extras.Mock;


public class ComerciosFragment extends Fragment {
    private List<Comercio> comercios;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        comercios = new ArrayList<>();
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
        comercios.add( Mock.criarComercioAleatorio() );
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comercios, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_comercios);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( getActivity() );
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration( divider );

        ComerciosAdapter mAdapter = new ComerciosAdapter( comercios );
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
