# -*- coding: utf-8 -*-

import copy
import numpy as np
from matplotlib import pyplot as plt
from matplotlib import animation as ani
from sklearn import linear_model,datasets
from skimage.measure import LineModel, ransac


n_samples = 14

x=[-2,0,2,3,4,5,6,8,10,12,13,14,16,18]
y=[0,0.9,2.0,6.5,2.9,8.8,3.95,5.03,5.97,7.1,1.2,8.2,8.5,10.1]

np.random.seed(seed=1)

data = np.column_stack([x, y])

model = LineModel()
model.estimate(data)

model_robust, inliers = ransac(data, LineModel, min_samples=2,
                               residual_threshold=1, max_trials=1000)
outliers = inliers == False

line_x = np.arange(-2, 18)
line_y = model.predict_y(line_x)
line_y_robust = model_robust.predict_y(line_x)

fig, ax = plt.subplots()
ax.plot(data[inliers, 0], data[inliers, 1], 'ob', alpha=0.8,
        label='Inlier data')
ax.plot(data[outliers, 0], data[outliers, 1], 'or', alpha=0.8,
        label='Outlier data')
ax.plot(line_x, line_y, '-k', label='general model')
ax.plot(line_x, line_y_robust, '-o', label='ransac model')
ax.legend(loc='high left')
plt.show()