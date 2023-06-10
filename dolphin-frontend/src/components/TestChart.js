import React, { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

const TestChart = () => {
  const [chartData, setChartData] = useState([]);

  useEffect(() => {
    fetchData();

    const interval = setInterval(fetchData, 2000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://3.111.108.14:4000/api/sensorDataStore/getSensorDetails');
      const { data } = await response.json();
      const filteredData = filterData(data);
      setChartData(filteredData);
    } catch (error) {
      console.error('Error fetching data from API:', error);
    }
  };

  const filterData = data => {
    const tenMinutesAgo = Date.now() - 10 * 60 * 1000; // 10 minutes in milliseconds
    return data
      .filter(item => new Date(item.sampleTakenAt).getTime() >= tenMinutesAgo)
      .map(item => ({
        month: item.sampleTakenAt,
        value: item.data
      }));
  };

  const handleClearData = async () => {
    try {
      await fetch('http://3.111.108.14:4000/api/sensorDataStore/deleteAll', { method: 'GET' });
      fetchData();
    } catch (error) {
      console.error('Error clearing data:', error);
    }
  };

  return (
    <div>
      <h2>Test Chart</h2>
      <button onClick={handleClearData}>Clear All Data</button>
      <LineChart width={500} height={300} data={chartData}>
        <XAxis dataKey="month" />
        <YAxis />
        <CartesianGrid stroke="#eee" strokeDasharray="5 5" />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="value" stroke="#8884d8" />
      </LineChart>
    </div>
  );
};

export default TestChart;
