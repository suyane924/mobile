import React, { useState } from 'react';
import { View, Text, Button, StyleSheet, TouchableOpacity } from 'react-native';

export default function App() {
  const [result, setResult] = useState('');
  
  const handlePress = (value) => {
    setResult(result + value);
  };

  const calculateResult = () => {
    try {
      setResult(eval(result).toString());
    } catch (error) {
      setResult('Erro');
    }
  };

  const clearResult = () => {
    setResult('');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.resultText}>{result}</Text>
      <View style={styles.buttonRow}>
        <Button title="1" onPress={() => handlePress('1')} />
        <Button title="2" onPress={() => handlePress('2')} />
        <Button title="3" onPress={() => handlePress('3')} />
        <Button title="+" onPress={() => handlePress('+')} />
      </View>
      <View style={styles.buttonRow}>
        <Button title="4" onPress={() => handlePress('4')} />
        <Button title="5" onPress={() => handlePress('5')} />
        <Button title="6" onPress={() => handlePress('6')} />
        <Button title="-" onPress={() => handlePress('-')} />
      </View>
      <View style={styles.buttonRow}>
        <Button title="7" onPress={() => handlePress('7')} />
        <Button title="8" onPress={() => handlePress('8')} />
        <Button title="9" onPress={() => handlePress('9')} />
        <Button title="*" onPress={() => handlePress('*')} />
      </View>
      <View style={styles.buttonRow}>
        <Button title="C" onPress={clearResult} />
        <Button title="0" onPress={() => handlePress('0')} />
        <Button title="=" onPress={calculateResult} />
        <Button title="/" onPress={() => handlePress('/')} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#f0f0f0',
  },
  resultText: {
    fontSize: 32,
    textAlign: 'right',
    marginBottom: 20,
    borderWidth: 1,
    padding: 10,
    borderRadius: 5,
    backgroundColor: '#ffffff',
  },
  buttonRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 20,
  },
});