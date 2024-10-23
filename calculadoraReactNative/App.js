import * as React from 'react';
import { useState } from 'react';
import { View, StyleSheet, TouchableOpacity } from 'react-native';
import { Text, Provider as PaperProvider } from 'react-native-paper';

export default function App() {
  const [expression, setExpression] = useState('');
  const [result, setResult] = useState('');

  const handlePress = (value) => {
    setExpression(expression + value);
  };

  const calculateResult = () => {
    try {
      setResult(eval(expression).toString());
    } catch (error) {
      setResult('Erro');
    }
  };

  const clearResult = () => {
    setExpression('');
    setResult('');
  };

  const renderButton = (label, onPress) => (
    <TouchableOpacity style={styles.button} onPress={onPress}>
      <Text style={styles.buttonText}>{label}</Text>
    </TouchableOpacity>
  );

  return (
    <PaperProvider>
      <View style={styles.container}>
        <Text style={styles.expressionText}>{expression}</Text>
        <Text style={styles.resultText}>{result}</Text>
        <View style={styles.buttonRow}>
          {renderButton('1', () => handlePress('1'))}
          {renderButton('2', () => handlePress('2'))}
          {renderButton('3', () => handlePress('3'))}
          {renderButton('+', () => handlePress('+'))}
        </View>
        <View style={styles.buttonRow}>
          {renderButton('4', () => handlePress('4'))}
          {renderButton('5', () => handlePress('5'))}
          {renderButton('6', () => handlePress('6'))}
          {renderButton('-', () => handlePress('-'))}
        </View>
        <View style={styles.buttonRow}>
          {renderButton('7', () => handlePress('7'))}
          {renderButton('8', () => handlePress('8'))}
          {renderButton('9', () => handlePress('9'))}
          {renderButton('*', () => handlePress('*'))}
        </View>
        <View style={styles.buttonRow}>
          {renderButton('C', clearResult)}
          {renderButton('0', () => handlePress('0'))}
          {renderButton('=', calculateResult)}
          {renderButton('/', () => handlePress('/'))}
        </View>
      </View>
    </PaperProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#f0f0f0',
  },
  expressionText: {
    fontSize: 24,
    textAlign: 'right',
    marginBottom: 10,
    padding: 10,
    backgroundColor: '#ffffff',
  },
  resultText: {
    fontSize: 32,
    textAlign: 'right',
    marginBottom: 20,
    padding: 10,
    backgroundColor: '#ffffff',
    color: '#6200ee',
  },
  buttonRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 20,
  },
  button: {
    flex: 1,
    margin: 5,
    backgroundColor: '#6200ee',
    padding: 15,
    alignItems: 'center',
    borderRadius: 5,
  },
  buttonText: {
    color: '#ffffff',
    fontSize: 20,
  },
});
