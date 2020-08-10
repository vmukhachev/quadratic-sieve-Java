package com.gazman.quadratic_sieve.core;

import com.gazman.quadratic_sieve.data.BSmooth;
import com.gazman.quadratic_sieve.data.DataQueue;
import com.gazman.quadratic_sieve.logger.Logger;
import com.gazman.quadratic_sieve.matrix.GaussianEliminationMatrix;

import java.math.BigInteger;

/**
 * Responsible for sieving
 */
public class Matrix implements Runnable{

    public static final Matrix instance = new Matrix();

    private final GaussianEliminationMatrix matrix = new GaussianEliminationMatrix();

    public void start(BigInteger N) {
        matrix.setN(N);
        new Thread(this).start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true){
            BSmooth bSmooth = null;
            try {
                bSmooth = DataQueue.bSmooths.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Logger.MATRIX.start();
            matrix.add(bSmooth);
            Logger.MATRIX.end();
        }
    }

    public int getSize() {
        return matrix.getSize();
    }
}
