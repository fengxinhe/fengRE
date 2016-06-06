# -*- coding: utf-8 -*-
"""
Created on Fri May  6 10:10:15 2016

@author: fengxinhe
"""

#x=[80,93,136,147,159,214,214,257,307,307]
#y=[144,232,275,131,69,31,152,83,62,231]
#label=[1,1,-1,-1,1,1,-1,1,-1,-1]

from numpy import *
from matplotlib import pyplot as pl
from matplotlib import animation as ani

date=[[80 , 144],
        [ 93 ,232],
        [ 136, 275],
        [ 147 , 131 ],
        [159 , 69 ],
        [214,31],
        [214,152],
        [257,83],
        [307,62],
        [307,231]]

def data_init():
    datmat = matrix([[80 , 144],
        [ 93 ,232],
        [ 136, 275],
        [ 147 , 131 ],
        [159 , 69 ],
        [214,31],
        [214,152],
        [257,83],
        [307,62],
        [307,231]])
    labels = [1,1,-1,-1,1,1,-1,1,-1,-1]
    return datmat,labels
  
def stump_classify(datmat,dimen,thresh,threshin):
    res=ones((shape(datmat)[0],1))
    if threshin == 'lt':
        res[datmat[:,dimen]<=thresh]=-1
    else:
        res[datmat[:,dimen]>thresh]=-1
    return res
#train the weak classifier using stump tree
def build_stump(datarr,labels,weit):
    datamat=mat(datarr)
    labelmat=mat(labels).T
    m,n=shape(datamat)
    steps=10.0
    beststump={}
    best_estim=mat(zeros((m,1)))
    minerror=inf
    for i in range(n):
        rangemin=datamat[:,i].min()
        rangemax=datamat[:,i].max()
        stepsize=(rangemax-rangemin)/steps
        for j in range(-1,int(steps)+1):
            for inequal in ['lt','gt']:
                thresh=(rangemin+float(j)*stepsize)
                predict=stump_classify(datamat,i,thresh,inequal)
                errarr=mat(ones((m,1)))
                errarr[predict==labelmat]=0
                weighterror=weit.T*errarr
                if weighterror<minerror:
                    minerror=weighterror
                    best_estim=predict.copy()
                    beststump['dim']=i
                    beststump['thresh']=thresh
    return beststump,minerror,best_estim
    
#alpha is the weight of the weak classifier
#wei is the weight of the train case
def ada_train(datarr,labels,numlt=40):
    weak_class=[]
    m=shape(datarr)[0]
    wei=mat(ones((m,1))/m)
    class_estim=mat(zeros((m,1)))
    count=0
    for i in range(numlt):
        count+=1
        best_stump,error,class_estim=build_stump(datarr,labels,wei)
        alpha= float(0.5*log((1.0-error)/max(error,1e-16)))
        best_stump['alpha']=alpha
        weak_class.append(best_stump)
        temp=multiply(-1*alpha*mat(labels).T,class_estim)
        wei=multiply(wei,exp(temp))
        wei=wei/wei.sum()
        class_estim+=alpha*class_estim
        aggErrors = multiply(sign(class_estim) != mat(labels).T,ones((m,1)))
        errorRate = aggErrors.sum()/m
        print "total error: ",errorRate
        if errorRate == 0.0: break
    return weak_class,class_estim,count
    

if __name__ == "__main__":
    datmat,labels=data_init()
    classifier,estimiation,cnum=ada_train(datmat,labels,10)
    print classifier,'\n'
    print 'weight of train cases','\n',estimiation,'\n'
    
    print 'final classifier','\n','labels =' ,'sign(',classifier[0]['alpha'],'*','weak(x,y)',1
    for i in range(1,cnum):
        print '+',classifier[i]['alpha'],'*','weak(x,y)',i+1
    print ')'
  
#display the animation of the classifier change in classify process
  
    fig = pl.figure()
    ax = pl.axes(xlim=(10, 320), ylim=(20, 300))
    line,=ax.plot([],[],'g',lw=2)
    line1,=ax.plot([],[],'orange',lw=4)
    line2,=ax.plot([],[],'orange',lw=4)
    line3,=ax.plot([],[],'orange',lw=4)

    x1=[]
    y1=[]
    x2=[]
    y2=[]
    def init():
        line.set_data([],[])
        cnt=0
        for p in date:
            if labels[cnt]>0:
                x1.append(p[0])
                y1.append(p[1])
            else:
                x2.append(p[0])
                y2.append(p[1])
            cnt+=1
        pl.plot(x1,y1,'or')
        pl.plot(x2,y2,'ob')
        line1.set_data([classifier[0]['thresh'],classifier[0]['thresh']],[classifier[2]['thresh'],400])
        line2.set_data([classifier[1]['thresh'],classifier[1]['thresh']],[0,classifier[2]['thresh']])
        line3.set_data([classifier[0]['thresh'],classifier[1]['thresh']],[classifier[2]['thresh'],classifier[2]['thresh']])
        
        return line,line1,line2,line3,
    
    def animate(i):
        global ax,line,classifier
        if classifier[i]['dim']<1:
            x1=classifier[i]['thresh']
            y1=10
            x2=x1
            y2=400
        else:
            y1=classifier[i]['thresh']
            x1=10
            y2=y1
            x2=400
        line.set_data([x1,x2],[y1,y2])
       
        return line,
    
    animat=ani.FuncAnimation(fig,animate,init_func=init,frames=cnum,interval=1000,repeat=True,
                                   blit=True)
    
    pl.show()
    #animat.save('/Users/dingpeien/adaboost.gif', fps=2,writer='imagemagick')  
 
    
    
    
    
    