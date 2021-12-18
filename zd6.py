def coef_tanimoto(n_a, n_b, n_c):
  return n_c / (n_a + n_b - n_c)

print('Введите два слова: ')

first_word = input().upper()
second_word = input().upper()

n_a = len(set(first_word))
n_b = len(set(second_word))
n_c = len(set(first_word).intersection(second_word))

print('Коэффициент похожести: ', coef_tanimoto(n_a, n_b, n_c))

